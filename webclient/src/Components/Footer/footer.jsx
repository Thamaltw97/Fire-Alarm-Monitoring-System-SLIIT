import React
    //, {Component}
    from 'react';
import { MDBFooter, MDBContainer } from "mdbreact";


const FooterHandler = () => {
    return(
        <MDBFooter color="blue" className="footer font-small pt-1 mt-5">
            <div className="footer-copyright text-center py-1">
                <MDBContainer fluid>
                    {/*<p>Made with &hearts; by Thamal Wijetunge.*/}
                    {/*All rights reserved. {new Date().getFullYear()}*/}
                    {/*</p>*/}
                    Made with ❤ by Team Runtime-ERROR.
                </MDBContainer>
            </div>
        </MDBFooter>
    );
};


export default FooterHandler;