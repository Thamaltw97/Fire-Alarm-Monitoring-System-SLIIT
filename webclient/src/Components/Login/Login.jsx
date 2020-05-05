import React, {Component} from "react";


export default class Login extends Component{

    render() {
        const {userEmail, userPassword, getEmail, getPassword, submit} = this.props;

        return(
            <div>
                <br/>
                <br/>
                <div align={"center"}>
                    <h1><font color={"#006400"}> Sign In </font></h1><br/><br/>
                </div>
                <div align={"center"}>
                    <div className={"col-md-4"} >
                        <form className={"px-4 py-3"} style={{backgroundColor: "#F0FFF0"}}onSubmit={submit}>
                            <div className="form-group" align={"left"}>

                                <label>Email Address:  </label><br/>
                                <input name="userEmail"
                                       type="email"
                                       className="form-control"
                                       placeholder="Email"
                                       onChange={getEmail}
                                       value={userEmail}
                                       required={true}/>

                            </div>

                            <div className="form-group" align={"left"}>
                                <label>Password:  </label><br/>
                                <input name="userPassword"
                                       type="password"
                                       className="form-control"
                                       placeholder="Password"
                                       onChange={getPassword}
                                       value={userPassword}
                                       required={true}/><br/><br/>
                            </div>

                            <button type="submit" className="btn btn-success btn-lg btn-block" >Sign In</button>

                        </form>
                    </div>
                </div>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
            </div>


        );
    }
}

