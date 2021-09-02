import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListEmployee from "./component/ListEmployee";
import Footer from "./component/Footer";
import Header from "./component/Header";
import CreateEmployee from "./component/CreateEmployee";
import UpdateEmployee from "./component/UpdateEmployee";
import ViewEmployeeComponent from "./component/ViewEmployee";

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

                        </Switch>
                    </div>
                    <Footer/>

            </Router>
        </div>
    );
}

export default App;
