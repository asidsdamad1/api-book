import {
    BrowserRouter,
    Routes,
    Route, Navigate,
} from "react-router-dom";
import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import List from "./pages/List/List";
import Single from "./pages/Single/Single";
import New from "./pages/New/New";
import {employeeInputs, productInputs, userInputs} from "./formSource";
import "./style/dark.scss"
import {useContext} from "react";
import {DarkModeContext} from "./Context/darkModeContext";
import {AuthContext} from "./Context/AuthContext";

function App() {
    const {darkMode} = useContext(DarkModeContext)

    const {currentUser} = useContext(AuthContext)

    const RequiredAuth = ({ children}) => {
        return currentUser ? children : <Navigate to={"/login"} />
    }

    
  return (
      <div className={darkMode ? "app dark" : "app"}>
          <BrowserRouter>
              <Routes>
                  <Route path={"/"}>
                      <Route path={"login"} element={<Login/>} />
                      <Route
                          index
                          element={
                              <RequiredAuth>
                                <Home/>
                              </RequiredAuth>
                          }
                      />
                      <Route path={"users"} >
                        <Route index element={<List/>}/>
                        <Route path={":userId"} element={<Single/>}/>
                        <Route path={"new"} element={<New inputs = {userInputs} title={"Add New User"} />}/>
                      </Route>
                      <Route path={"products"} >
                          <Route index element={<List/>}/>
                          <Route path={":productId"} element={<Single/>}/>
                          <Route path={"new"} element={<New  inputs = {productInputs} title={"Add New Product"}/>}/>
                      </Route>
                      <Route path={"employees"} >
                          <Route index element={<List/>}/>
                          <Route path={":employeeId"} element={<Single/>}/>
                          <Route path={"new"} element={<New  inputs = {employeeInputs} title={"Add New Employee"}/>}/>
                      </Route>
                  </Route>
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
