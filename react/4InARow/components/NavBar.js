import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import AppScreen from './AppScreen';

export default class NavBar extends Component {
    render() {
        return (
            <View style={ styles.NavContainer }>
                <View style={ styles.NavBox }>
                    <Text style={ styles.NavText } onPress={ () => this.props.changeScreen(AppScreen.INVALIDATE)}>New Game</Text>
                </View>
                <View style={ styles.NavBox }>
                    <Text style={ styles.TitleText }>Four In a Row</Text>
                </View>
                <View style={ styles.NavBox }>
                    <Text style={ styles.NavText } onPress={ () => this.props.changeScreen(AppScreen.SETTINGSSCREEN) }>Settings</Text>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    NavContainer: {
        backgroundColor: "#444444",
        borderBottomWidth: 2,
        borderColor: "#aaaaaa",
        height: '10%',
        display: "flex",
        flexDirection: "row"
    },
    NavBox: {
        display: "flex",
        flexGrow: 1,
        color: '#333333',
        borderWidth: 2,
        borderBottomWidth: 0,
        borderColor: '#aaaaaa',
        justifyContent: "center",
        alignItems: "center",
    },
    NavText: {
        width: "100%",
        height: "100%",
        color: '#ffffff',
        textAlign: "center",
        textAlignVertical: "center",
    },
    TitleText: {
        color: '#44cc44',
        fontSize: 20,
        marginLeft: 40,
        marginRight: 40,
    }
});