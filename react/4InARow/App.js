import React, { Component } from 'react';
import NavBar from './components/NavBar';
import { ScreenOrientation } from 'expo';
import AppScreen from './components/AppScreen';

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      currentScreen: AppScreen.GAMESCREEN,
      lastScreenVars: null,
    }
  }

  updateScreen = (newScreen, lastScreenVars=null) => {
    this.setState({
      currentScreen: newScreen,
      lastScreenVars: lastScreenVars,
    });
  }

  static componentDidMount() {
    ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.LANDSCAPE);
  }

  render() {

    return (
      <React.Fragment>
        <NavBar changeScreen={ this.updateScreen } />
        <AppScreen screen={ this.state.currentScreen } changeScreen={ this.updateScreen } lastScreenVars={ this.state.lastScreenVars }/>
      </React.Fragment>
    );
  }
}

export default App;