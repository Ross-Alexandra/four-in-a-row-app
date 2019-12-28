import React, { Component } from 'react';
import { StyleSheet, View, TouchableOpacity } from 'react-native';

export default class GameScreen extends Component {

    static UNPLAYED = 0;
    static PLAYERONE = 1;
    static PLAYERTWO = 2;

    static NUMROWS = 6;
    static NUMCOLS = 7;

    constructor(props) {
        super(props);

        let gameState = [[], [], [], [], [], []];
        let nextMove = [5, 5, 5, 5, 5, 5, 5];
        for (let i = 0; i < GameScreen.NUMROWS; i++) {
            for (let j = 0; j < GameScreen.NUMCOLS; j++) {
                gameState[i].push(GameScreen.UNPLAYED);
            }
        }
        
        this.state = {
            gameState, // The current board.
            currentPlayer: GameScreen.PLAYERONE, // Next player to play a move.
            nextMove, // An array to tell you which column the next play is in each row.
        }
    }

    static getDerivedStateFromProps(props, state) {
        return null;
    }

    playMove = (col) => {
        console.log(`playing move in col ${col + 1}`);

        if (this.state.nextMove[col] == -1) {
            return;
        }

        let gameBoard = [...this.state.gameState];
        console.log(gameBoard);
        gameBoard[this.state.nextMove[col]][col] = this.state.currentPlayer;

        let nextMove = [...this.state.nextMove]
        nextMove[col]--;

        let currentPlayer = this.state.currentPlayer != GameScreen.PLAYERONE ? GameScreen.PLAYERONE : GameScreen.PLAYERTWO;

        this.setState({
            gameState: gameBoard,
            nextMove,
            currentPlayer,
        });
    }

    render() {
        let gameCells = [[],[],[],[],[],[]];

        for (let i = 0; i < GameScreen.NUMROWS; i++) {
            for (let j = 0; j < GameScreen.NUMCOLS; j++) {
                const key = `(${i},${j})`;
                
                let color_style;
                if (this.state.gameState[i][j] == GameScreen.UNPLAYED) {
                    color_style = { backgroundColor: "#444444" };
                } else if (this.state.gameState[i][j] == GameScreen.PLAYERONE) {
                    color_style = { backgroundColor: "#cc3311" };
                } else if (this.state.gameState[i][j] == GameScreen.PLAYERTWO) {
                    color_style = { backgroundColor: "#1133cc" };
                }
                else {
                    color_style = {};
                }

                const circle_style = Object.assign({}, styles.cell_circle, color_style);
                gameCells[i].push(
                    <TouchableOpacity activeOpacity={ 1 } key={key} style={ styles.game_cell } onPress={ () => { this.playMove(j); } }>
                        <TouchableOpacity activeOpacity={ 1 } style={ circle_style } onPress={ () => { this.playMove(j); } }/>
                    </TouchableOpacity>);
            }
        }

        return (
            <View style={ styles.main_container }>
                <View style={ styles.game_board }>
                    { gameCells }
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    main_container: {
        flex: 1,
        display: "flex",
        backgroundColor: '#444444',
        flexDirection: "column",
        alignItems: 'center',
        justifyContent: 'center',
    },
    game_board: {
        display: "flex",
        flexDirection: "row",
        width: "100%",
        height: "85%",
        maxWidth: "77%",
        flexWrap: "wrap",
        borderWidth: 3,
        borderColor: "#111111",
    },
    game_cell: {
        flex: 1,
        backgroundColor: "#cccccc",
        minWidth: "10.5%",
        minHeight: "16.66%",
        borderWidth: 1,
        borderColor: '#dddddd'
    },
    cell_circle: {
        marginTop: "7%",
        height: "13%",
        marginLeft: "25%",
        marginRight: "25%",
        borderRadius: 50,
        borderWidth: 2,
        borderColor: '#111111'
    }
});
  