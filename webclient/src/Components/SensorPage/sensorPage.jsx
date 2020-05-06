import React,{Component} from "react";


export default class SensorPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            content: []
        };

        this.fetchData = this.fetchData.bind(this);
    }

    componentWillMount() {
        this.fetchData();
        setInterval(this.fetchData, 40000);
    }


    fetchData = () =>{
        const req = new Request('http://localhost:44381/api/sensor/getsensors', {
            method: 'GET',
            cache: 'default'
        });

        fetch(req).then(response =>{
            return response.json();
        }).then(data =>{
            console.log(data);
            this.setState({
                content: data.msg
            });
        }).catch(err => {
            console.log("ERROR: " + err);
        })
    };


    render() {

        const { content } = this.state;

        return (
            <div id="Body">

                <div className="row m-5" id="Sensor">
                    {content.map((item, sensor) => (
                        <div className="col-md-3" key={sensor}>
                            <div className="m-5" >
                                <div className=
                                         {(item.sensorStatus === "I") ? "card text-black border-black mb-3" : (item.coLevel > 5 || item.smokeLevel > 5) ? "card text-white bg-danger mb-3" : "card text-white bg-success mb-3"} >
                                    <div className={(item.coLevel > 5 || item.smokeLevel > 5) ?"card-header border-danger": "card-header border-success"}  id="cardHeader"><h5 className="card-title">{item.sensorName}</h5></div>
                                    <div className="card-body " id="cardBody" >
                                        <p><span>Floor No : {item.floorNo}</span></p>
                                        <p><span>Room No : {item.roomNo}</span></p>
                                        <p><span>{(item.sensorStatus === "I") ? "Smoke Level : N/A" : "Smoke Level : " + (item.smokeLevel)}</span></p>
                                        <p><span>{(item.sensorStatus === "I") ? "CO2 Level : N/A" : "CO2 Level : " + (item.coLevel)}</span></p>
                                        <p><span>{(item.sensorStatus === "A") ? "Status : Active" : "Status : Inactive"}</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}

                </div>

            </div>
        );
    }
}
