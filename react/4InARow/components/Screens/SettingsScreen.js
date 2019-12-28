import React, { Component } from 'react';
import { View, Button, Text, StyleSheet } from 'react-native';
import AppScreen from '../AppScreen';

export default class SettingsScreen extends Component {
    render() {
        return (
        <View style={ styles.mainContainer }>
            <View style={ styles.subContainer }>
                <Text>There are currently no settings that can be changed.</Text>
            </View>
            <View style={ styles.subContainer }>
                <Button
                    title="Please return to game"
                    onPress={ () => this.props.changeScreen(AppScreen.GAMESCREEN) }
                />
            </View>
        </View>
        );
    }
}

const styles = StyleSheet.create({
    mainContainer: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        padding: "10%",
    },
    subContainer: {
        flex: 1,
        paddingTop: "2.5%",
        paddingBottom: "2.5%"
    }
})