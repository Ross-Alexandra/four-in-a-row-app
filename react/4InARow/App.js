import React, { Component } from 'react';
import NavBar from './components/NavBar';
import { ScreenOrientation } from 'expo';
import AppScreen from './components/AppScreen';

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      currentScreen: AppScreen.GAMESCREEN
    }
  }

  updateScreen = (newScreen) => {
    this.setState({ currentScreen: newScreen });
  }

  static componentDidMount() {
    ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.LANDSCAPE);
  }

  render() {

    return (
      <React.Fragment>
        <NavBar changeScreen={ this.updateScreen } />
        <AppScreen screen={ this.state.currentScreen } changeScreen={ this.updateScreen }/>
      </React.Fragment>
    );
  }
}

export default App;