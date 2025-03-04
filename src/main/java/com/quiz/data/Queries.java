package com.quiz.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enthält SQL-Abfragen für das Quiz.
 */
public class Queries {
    private static final Logger logger = LoggerFactory.getLogger(Queries.class);
    private Connection connection;

    public Queries() {
        this.connection = new ConnectionManager().getConnection();
    }

    /**
     * Ruft eine definierte Anzahl an Fragen aus der Datenbank ab.
     * @param category    Die Kategorie-ID der Fragen.
     * @param difficulty  Die Schwierigkeits-ID.
     * @param connection  Die Datenbankverbindung.
     * @param limit       Anzahl der gewünschten Fragen.
     * @return Liste von Fragen.
     */
    public List<Question> getQuestions(int category, int difficulty, Connection connection, int limit) {
        if (connection == null) {
            logger.warn("Keine Datenbankverbindung! Fragen können nicht geladen werden.");
            return new ArrayList<>();
        }

        List<Question> questions = new ArrayList<>();
        Map<Integer, Question> questionMap = new HashMap<>(); 
        String query = """
                SELECT q.id AS question_id, q.question, c.category, d.difficulty, 
                       o.id AS option_id, o.option_text, o.is_correct
                FROM questions q
                JOIN categories c ON q.category_id = c.id
                JOIN difficulties d ON q.difficulty_id = d.id
                LEFT JOIN options o ON q.id = o.question_id
                WHERE c.id = ? AND d.id = ?
                ORDER BY q.id, RAND()
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, category);
            stmt.setInt(2, difficulty);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                Question question = questionMap.get(questionId);

                if (question == null) {
                    String questionText = rs.getString("question");
                    String categoryName = rs.getString("category");
                    String difficultyName = rs.getString("difficulty");
                    question = new Question(questionId, questionText, categoryName, difficultyName);
                    questionMap.put(questionId, question);
                }

                int optionId = rs.getInt("option_id");
                if (optionId > 0) {
                    String optionText = rs.getString("option_text");
                    boolean isCorrect = rs.getBoolean("is_correct");
                    question.addOption(new Option(optionId, optionText, isCorrect));
                }
            }

            for (Question q : questionMap.values()) {
                while (q.getOptions().size() < 4) {
                    q.addOption(new Option(-1, "Keine Antwort verfügbar", false));
                }
            }

            questions = new ArrayList<>(questionMap.values());
            Collections.shuffle(questions); 
            questions = questions.subList(0, Math.min(questions.size(), limit));

            logger.info("Erfolgreich {} Fragen mit jeweils 4 Antwortmöglichkeiten geladen.", questions.size());
        } 
        
        catch (SQLException e) {
            logger.error("Fehler beim Abrufen der Fragen.", e);
        }

        return questions;
    }

    /**
     * Speichert den Highscore eines Spielers in der Datenbank.
     * Falls der Spieler bereits existiert, wird der Score nur überschrieben, wenn er höher ist.
     * @param username Spielername.
     * @param score    Erreichter Punktestand.
     */
    public void saveUserScore(String username, int score) {
        if (connection == null) {
            logger.warn("Keine Datenbankverbindung! Score kann nicht gespeichert werden.");
            return;
        }

        String selectQuery = "SELECT score FROM userdata WHERE username = ?";
        String insertQuery = "INSERT INTO userdata (username, score, date) VALUES (?, ?, NOW())";
        String updateQuery = "UPDATE userdata SET score = ?, date = NOW() WHERE username = ? AND score < ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int existingScore = rs.getInt("score");
                if (score > existingScore) {
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, score);
                        updateStmt.setString(2, username);
                        updateStmt.setInt(3, score);
                        updateStmt.executeUpdate();
                        logger.info("Highscore für {} aktualisiert: {} Punkte.", username, score);
                    }
                } 
                
                else {
                    logger.info("Neuer Score ({}) ist niedriger als bestehender Highscore ({}). Kein Update nötig.", score, existingScore);
                }
            } 
            
            else {
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, username);
                    insertStmt.setInt(2, score);
                    insertStmt.executeUpdate();
                    logger.info("Neuer Spieler {} mit Score {} gespeichert.", username, score);
                }
            }
        } 
        
        catch (SQLException e) {
            logger.error("Fehler beim Speichern des Scores für {}.", username, e);
        }
    }


    /**
     * Ruft das Leaderboard aus der Datenbank ab.
     * @return Liste der Top-10 Spieler mit Score.
     */
    public List<String> getLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        String query = "SELECT username, score FROM userdata ORDER BY score DESC LIMIT 10";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                int score = rs.getInt("score");
                leaderboard.add(username + " - " + score);
            }
            logger.info("Leaderboard erfolgreich geladen ({} Einträge).", leaderboard.size());
        } 
        
        catch (SQLException e) {
            logger.error("Fehler beim Abrufen des Leaderboards.", e);
        }

        return leaderboard;
    }
}
