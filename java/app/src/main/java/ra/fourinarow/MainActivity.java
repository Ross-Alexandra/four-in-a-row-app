package ra.fourinarow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Game game;
    boolean hasWon = false;
    String winner = "";

    @Override
    protected void onCreate(Bundle savedInstanceBUTTONLOG) {
        super.onCreate(savedInstanceBUTTONLOG);
        setContentView(R.layout.activity_main);

        //Define the ImageView cells that will be used in the app.
        ImageView[][] cells = {
                {findViewById(R.id.Cell00), findViewById(R.id.Cell01), findViewById(R.id.Cell02),
                        findViewById(R.id.Cell03), findViewById(R.id.Cell04),
                        findViewById(R.id.Cell05), findViewById(R.id.Cell06)},
                {findViewById(R.id.Cell10), findViewById(R.id.Cell11), findViewById(R.id.Cell12),
                        findViewById(R.id.Cell13), findViewById(R.id.Cell14),
                        findViewById(R.id.Cell15), findViewById(R.id.Cell16)},
                {findViewById(R.id.Cell20), findViewById(R.id.Cell21), findViewById(R.id.Cell22),
                        findViewById(R.id.Cell23), findViewById(R.id.Cell24),
                        findViewById(R.id.Cell25), findViewById(R.id.Cell26)},
                {findViewById(R.id.Cell30), findViewById(R.id.Cell31), findViewById(R.id.Cell32),
                        findViewById(R.id.Cell33), findViewById(R.id.Cell34),
                        findViewById(R.id.Cell35), findViewById(R.id.Cell36)},
                {findViewById(R.id.Cell40), findViewById(R.id.Cell41), findViewById(R.id.Cell42),
                        findViewById(R.id.Cell43), findViewById(R.id.Cell44),
                        findViewById(R.id.Cell45), findViewById(R.id.Cell46)},
                {findViewById(R.id.Cell50), findViewById(R.id.Cell51), findViewById(R.id.Cell52),
                        findViewById(R.id.Cell53), findViewById(R.id.Cell54),
                        findViewById(R.id.Cell55), findViewById(R.id.Cell56)}
        };

        //Create a new game object.
        game = new Game(Game.MULTIPLAYER, cells);

        //Declare the buttons used in the grid.
        Button row1 = findViewById(R.id.Row1);
        Button row2 = findViewById(R.id.Row2);
        Button row3 = findViewById(R.id.Row3);
        Button row4 = findViewById(R.id.Row4);
        Button row5 = findViewById(R.id.Row5);
        Button row6 = findViewById(R.id.Row6);
        Button row7 = findViewById(R.id.Row7);

        //Setup button listeners.
        row1.setOnClickListener(this);
        row2.setOnClickListener(this);
        row3.setOnClickListener(this);
        row4.setOnClickListener(this);
        row5.setOnClickListener(this);
        row6.setOnClickListener(this);
        row7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (!hasWon) switch (v.getId()) {
            case R.id.Row1:
                hasWon = game.playCol(1);
                Log.d("BUTTONLOG", "Button 1 pressed.");

                break;
            case R.id.Row2:
                hasWon = game.playCol(2);
                Log.d("BUTTONLOG", "Button 2 pressed.");

                break;
            case R.id.Row3:
                hasWon = game.playCol(3);
                Log.d("BUTTONLOG", "Button 3 pressed.");

                break;
            case R.id.Row4:
                hasWon = game.playCol(4);
                Log.d("BUTTONLOG", "Button 4 pressed.");

                break;
            case R.id.Row5:
                hasWon = game.playCol(5);
                Log.d("BUTTONLOG", "Button 5 pressed.");

                break;
            case R.id.Row6:
                hasWon = game.playCol(6);
                Log.d("BUTTONLOG", "Button 6 pressed.");

                break;
            case R.id.Row7:
                hasWon = game.playCol(7);
                Log.d("BUTTONLOG", "Button 7 pressed.");

                break;
        }
    }
}

class Game {

    private FourInARow core;
    private ImageView[][] cells;
    public static final int MULTIPLAYER = 0;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    Game(int difficulty, ImageView[][] cells) {
        if (difficulty == MULTIPLAYER) {
            core = new FourInARow("Player1", "Player2");
        }
        else {
            core = new FourInARow("Player1", difficulty);
        }

        this.cells = cells;
    }

    boolean playCol(int Column) {

        //Go from position to index.
        Column--;

        boolean win;
        int[] cell = {(core.ROW_MAX -1 ) - core.getRow(Column), Column};

        try {
            win = core.playCol(Column);
        }
        catch (ColumnFullException e) {
            return false;
        }

        Log.d("CELLINFO", Arrays.toString(cell));
        int player_value = core.getCell(cell[0], cell[1]);

        Log.d("CELLINFO: PLAYER_VALUE:", player_value + "");
        if (player_value == 1) {
            cells[cell[0]][cell[1]].setColorFilter(Color.rgb(0, 0, 255));
            cells[cell[0]][cell[1]].setVisibility(View.VISIBLE);
        }
        else if (player_value == -1) {
            cells[cell[0]][cell[1]].setColorFilter(Color.rgb(255, 0, 0));
            cells[cell[0]][cell[1]].setVisibility(View.VISIBLE);
        }

        return win;
    }
}
