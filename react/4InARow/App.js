import React, { Component } from 'react';
import NavBar from './components/NavBar';
import { ScreenOrientation } from 'expo';
import AppScreen from './components/AppScreen';

class App extends Component {

  render() {
    ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.LANDSCAPE);

    return (
      <React.Fragment>
        <NavBar/>
        <AppScreen screen={ AppScreen.GAMESCREEN }/>
      </React.Fragment>
    );
  }
}

export default App;