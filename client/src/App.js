
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListEmployee from "./component/Employee/ListEmployee";
import Footer from "./component/Footer";
import Header from "./component/Header";
import CreateEmployee from "./component/Employee/CreateEmployee";
import ViewEmployeeComponent from "./component/Employee/ViewEmployee";

import  'react-app-polyfill/stable'
import Login from "./component/User/Login";
import Profile from "./component/User/Profile";

function App() {
    return (

        <div>
            <Router>
                    <Header/>
                    <div className="container">
                        <Switch>
                            <Route path={"/"} exact component={ListEmployee}/>
                            <Route path={"/employees"} component={ListEmployee}/>
                            <Route path={"/add-employee/:id"} component={CreateEmployee}/>
                            <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}/>
                            {/*<Route path={"/update-employee/:id"} component={UpdateEmployee}/>*/}
                            <Route path='/signin' exact={true} component={Login}/>
                            <Route path='/profile' exact={true} component={Profile}/>

                        </Switch>
                    </div>
                    <Footer/>

            </Router>
        </div>
    );
}

export default App;
