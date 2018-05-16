package com.example.android.courtcounter;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;
    int playersA = 7;
    int playersB = 7;
    int previousScoreA = scoreA;
    int previousScoreB = scoreB;
    int previousPlayersA = playersA;
    int previousPlayersB = playersB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // Saves the current state of all variables so that the "undo" function can work
    public void saveState() {
        previousScoreA = scoreA;
        previousScoreB = scoreB;
        previousPlayersA = playersA;
        previousPlayersB = playersB;
    }

    // Displays the given score for Team A.
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    // Displays the number of Team A members in-play.
    public void teamAPlayers(int num_players) {
        TextView scoreView = findViewById(R.id.team_a_players);
        String players = "Players = " + num_players;
        scoreView.setText(players);
    }

    // Adds a bonus point to Team A due to a quarter-line touch and successful return
    // No player is added as a result the quarter-line touch.
    public void quarterLineA(View v) {
        saveState();
        scoreA = scoreA + 1;
        displayForTeamA(scoreA);
    }

    // Adds a bonus point for Team A for a raider's extraction of more than 2 Team B players
    // No player is added as a result of the bonus point.
    public void bonusA(View v) {
        saveState();
        scoreA = scoreA + 1;
        displayForTeamA(scoreA);
    }

    // Adds 1 to the score, for each player of Team B removed
    // Auto-deducts Team B player and adds Team A player
    public void raiderAScore(View v) {
        saveState();
        scoreA = scoreA + 1;
        if (playersA < 7) {
            playersA++;
        }
        playersB--;
        if (playersB < 1) {
            scoreA = scoreA + 2;
            playersB = 0;
            Toast.makeText(getApplicationContext(), R.string.match_over, Toast.LENGTH_LONG).show();
            displayForTeamA(scoreA);
            teamBPlayers(playersB);
            return;
        }
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }

    // This adds a point to Team A, deducts a Team B member, and adds a Team A member (as available)
    public void teamATackle(View v) {
        saveState();
        scoreA = scoreA + 1;
        if (playersA < 7) {
            playersA++;
        }
        playersB--;
        if (playersB < 1) {
            scoreA = scoreA + 2;
            playersB = 0;
            Toast.makeText(getApplicationContext(), R.string.match_over, Toast.LENGTH_LONG).show();
            displayForTeamA(scoreA);
            teamBPlayers(playersB);
            return;
        }
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }

    // Team B Empty Raid - results in a bonus point to Team A
    public void emptyRaidB(View v) {
        saveState();
        scoreA = scoreA + 1;
        displayForTeamA(scoreA);
    }

    // Displays the given score for Team B.
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    // Displays the number of Team B members in-play.
    public void teamBPlayers(int num_players) {
        TextView scoreView = findViewById(R.id.team_b_players);
        String players = "Players = " + num_players;
        scoreView.setText(players);
    }

    // Adds a bonus point to Team B due to a quarter-line touch and successful return
    // No player is added as a result the quarter-line touch.
    public void quarterLineB(View v) {
        saveState();
        scoreB = scoreB + 1;
        displayForTeamB(scoreB);
    }

    // Adds a bonus point for Team B for a raider's extraction of more than 2 Team A players
    // No player is added as a result of the bonus point.
    public void bonusB(View v) {
        saveState();
        scoreB = scoreB + 1;
        displayForTeamB(scoreB);
    }

    // Adds 1 to the score, for each player of Team A removed
    // Auto-deducts Team A player and adds Team B player
    public void raiderBScore(View v) {
        saveState();
        scoreB = scoreB + 1;
        if (playersB < 7) {
            playersB++;
        }
        playersA--;
        if (playersA < 1) {
            scoreB = scoreB + 2;
            playersA = 0;
            Toast.makeText(getApplicationContext(), R.string.match_over, Toast.LENGTH_LONG).show();
            displayForTeamB(scoreB);
            teamAPlayers(playersA);
            return;
        }
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }

    // This adds a point to Team B, deducts a Team A member, and adds a Team B member (as available)
    public void teamBTackle(View v) {
        saveState();
        scoreB = scoreB + 1;
        if (playersB < 7) {
            playersB++;
        }
        playersA--;
        if (playersA < 1) {
            scoreB = scoreB + 2;
            playersA = 0;
            Toast.makeText(getApplicationContext(), R.string.match_over, Toast.LENGTH_LONG).show();
            displayForTeamB(scoreB);
            teamAPlayers(playersA);
            return;
        }
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }

    // Team A Empty Raid - results in a bonus point to Team B
    public void emptyRaidA(View v) {
        saveState();
        scoreB = scoreB + 1;
        displayForTeamB(scoreB);
    }

    // Allows the user to "back up" one step in the scores and number of player (just a reset of the previous state)
    public void undo(View v) {
        scoreA = previousScoreA;
        scoreB = previousScoreB;
        playersA = previousPlayersA;
        playersB = previousPlayersB;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }

    // Resets score to zero
    public void reset(View v) {
        scoreA = 0;
        scoreB = 0;
        playersA = 7;
        playersB = 7;
        previousPlayersA = playersA;
        previousPlayersB = playersB;
        previousScoreA = scoreA;
        previousScoreB = scoreB;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
        teamAPlayers(playersA);
        teamBPlayers(playersB);
    }
}
