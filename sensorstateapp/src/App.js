import React from 'react';
//import logo from './logo.svg';
import './App.css';
import axios from 'axios';

class App extends React.Component {


    //This helps to call handleSensorUpdate() function in every 10 seconds
    componentWillMount () {
        this.handleSensorUpdate();
        setInterval(this.handleSensorUpdate, 10000);
    }

    //Main function to update smoke and co2 levels
    handleSensorUpdate () {

        //Passing POST Json objects to each sensor to update levels
        const obj = {
            sensorId: 1,
            smokeLevel: Math.floor(Math.random() * 10) + 1,
            coLevel: Math.floor(Math.random() * 10) + 1
        };
        axios
            .post('http://localhost:44381/api/sensor/setsensorlevel', obj)
            .then((res) => console.log(res))
            .catch((err) => console.log(err));

    }

  render() {
    return (
        <div>
          <h2 align="center">Sensor Updating App is running...</h2>
        </div>
    );
  }
}

export default App;
