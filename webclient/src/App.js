import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Main from "./Components/Main/main";
import FooterHandler from "./Components/Footer/footer";

function App() {
    return (
        <div>
            <div>
                <Main />
            </div>
            <div>
                <FooterHandler />
            </div>
        </div>
    );
}

export default App;
