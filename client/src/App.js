
import './App.css';
import {
    BrowserRouter,
    Routes,
    Route,
} from "react-router-dom";
import ListEmployee from "./component/Employee/ListEmployee";
import Footer from "./component/Footer";
import Header from "./component/Header";
import CreateEmployee from "./component/Employee/CreateEmployee";
import ViewEmployeeComponent from "./component/Employee/ViewEmployee";

import  'react-app-polyfill/stable'
import Profile from "./component/User/Profile";
import Register from "./component/User/Register";
import Home from "./component/Home/Home";
import Login from "./component/Login/Login";

function App() {
    return (
        //
        // <div>
        //     <Router>
        //         <Header/>
        //             <div className="container">
        //                 <Switch>
        //                     <Route path={"/"} exact component={ListEmployee}/>
        //                     <Route path={"/employees"} component={ListEmployee}/>
        //                     <Route path={"/add-employee/:id"} component={CreateEmployee}/>
        //                     <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}/>
        //                     {/*<Route path={"/update-employee/:id"} component={UpdateEmployee}/>*/}
        //                     <Route path='/signin' exact={true} component={Login}/>
        //                     <Route path='/profile' exact={true} component={Profile}/>
        //                     <Route path='/signup' exact={true} component={Register}/>
        //
        //                 </Switch>
        //             </div>
        //             <Footer/>
        //
        //     </Router>
        // </div>
        <div className={App}>
            <BrowserRouter>
                <Routes>
                    <Route path={"/"}>
                        <Route index element={<Home />} />
                        <Route  path={"login"} element={<Login />} />
                    </Route>
                </Routes>
            </BrowserRouter>
            <Home />
        </div>
    );
}

export default App;
