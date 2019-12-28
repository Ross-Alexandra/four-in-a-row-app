import React, { Component } from 'react';
import GameScreen from './Screens/GameScreen';
import SettingsScreen from './Screens/SettingsScreen';

export default class AppScreen extends Component {

    static GAMESCREEN = 0;
    static SETTINGSSCREEN = 1;

    render() {
        let currentScreen;
        if (this.props.screen == AppScreen.GAMESCREEN) {
            currentScreen = <GameScreen/>;
        }
        else if (this.props.screen == AppScreen.SETTINGSSCREEN) {
            currentScreen = <SettingsScreen/>;
        }

        return (
                <React.Fragment>
                    { currentScreen }
                </React.Fragment>
            );
    }
}