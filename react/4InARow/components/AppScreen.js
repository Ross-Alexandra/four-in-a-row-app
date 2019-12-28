import React, { Component } from 'react';
import GameScreen from './Screens/GameScreen';
import SettingsScreen from './Screens/SettingsScreen';

export default class AppScreen extends Component {

    static GAMESCREEN = 0;
    static SETTINGSSCREEN = 1;

    constructor(props) {
        super(props);

        this.state = { 
            currentScreen: null,
            freshScreen: false,
        }
    }

    render() {
        let currentScreen;

        if (this.props.screen != this.state.currentScreen) {
            this.setState({
                currentScreen: this.props.screen,
                freshScreen: true
            });
        }

        if (this.state.screen == AppScreen.GAMESCREEN) {
            currentScreen = <GameScreen changeScreen={ this.props.changeScreen } newGame={ this.state.freshScreen }/>;
            if (this.state.freshScreen) {
                this.setState({ freshScreen: false });
            }
        }
        else if (this.state.screen == AppScreen.SETTINGSSCREEN) {
            currentScreen = <SettingsScreen changeScreen={ this.props.changeScreen }/>;
        }

        return (
                <React.Fragment>
                    { currentScreen }
                </React.Fragment>
            );
    }
}