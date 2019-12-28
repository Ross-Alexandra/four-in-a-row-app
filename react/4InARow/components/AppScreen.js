import React, { Component } from 'react';
import { Text } from 'react-native';
import GameScreen from './Screens/GameScreen';
import SettingsScreen from './Screens/SettingsScreen';

export default class AppScreen extends Component {

    static INVALIDATE = -1;
    static GAMESCREEN = 0;
    static SETTINGSSCREEN = 1;

    constructor(props) {
        super(props);

        this.state = { 
            currentScreen: AppScreen.GAMESCREEN,
            freshScreen: false,
        }
    }

    static getDerivedStateFromProps(props, state) {
        if (props.screen != state.currentScreen) {
            let currentScreen = props.screen != AppScreen.INVALIDATE ? props.screen : AppScreen.GAMESCREEN

            return {
                currentScreen: currentScreen,
                freshScreen: true
            }
        }

        else if (state.freshScreen) {
            return {
                freshScreen: false
            }
        }

        return null;
    }

    render() {
        let currentScreen;

        if (this.state.currentScreen == AppScreen.GAMESCREEN) {
            currentScreen = <GameScreen changeScreen={ this.props.changeScreen } newGame={ this.state.freshScreen }/>;
        }
        else if (this.state.currentScreen == AppScreen.SETTINGSSCREEN) {
            currentScreen = <SettingsScreen changeScreen={ this.props.changeScreen }/>;
        }
        else {
            currentScreen = <Text>Loading...</Text>;
        }

        return (
                <React.Fragment>
                    { currentScreen }
                </React.Fragment>
            );
    }
}