import React, { Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default class GameScreen extends Component {

    render() {
        return (
            <View style={ styles.main_container }>
                <Text style={ { color: '#eeeeee' } }>This is the main body of the screen</Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    main_container: {
        flex: 1,
        backgroundColor: '#111111',
        alignItems: 'center',
        justifyContent: 'center',
    },
    tile_container: {
        borderWidth: 10,
        borderColor: '#ffffff',
    },
});
  