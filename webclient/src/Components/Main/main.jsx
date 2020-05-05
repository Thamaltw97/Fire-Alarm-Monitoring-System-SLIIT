import React,{Component} from "react";
import Login from "../Login/Login";
import SensorPage from "../SensorPage/sensorPage";
const axios = require('axios').default;


export default class Main extends Component{

    state = {
        userEmail : '',
        userPassword: '',
        goToPage: false,
        error: '',
        status: null,
        results: [],

    };

    getEmail = (e) => {
        e.preventDefault();
        this.setState({
            userEmail:e.target.value
        })
    };

    getPassword = (e) => {
        e.preventDefault();
        this.setState({
            userPassword:e.target.value
        })
    };

    submit = (e) =>{
        e.preventDefault();

        // Send a POST request
        axios
            .post("http://localhost:44381/api/user/signin", {
                userEmail: this.state.userEmail.toString(),
                userPassword: this.state.userPassword.toString()
            })
            .then(result => {
                this.setState({
                    status: result.status,
                    results:result,
                    error: false,
                });
                if (this.state.results.statusText === "OK"){
                    this.setState({
                        goToPage:true
                    })
                }
                else{
                    alert('Wrong Credentials !');
                };
            })
            .catch(err => console.error(err));
    };


    render() {

        return (
            <div>
                {this.state.goToPage ?
                    <SensorPage/> : <Login
                        userEmail={this.state.email}
                        userPassword={this.state.password}
                        getEmail={this.getEmail}
                        getPassword={this.getPassword}
                        submit={this.submit}/>}
            </div>
        );
    }
}
