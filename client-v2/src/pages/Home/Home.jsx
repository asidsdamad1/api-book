import Sidebar from "../../Components/Sidebar/Sidebar";
import "./home.scss"
import Navbar from "../../Components/Navbar/Navbar";
import Widget from "../../Components/widget/Widget";
import Chart from "../../Components/Chart/Chart";
import Featured from "../../Components/Featured/Featured";

function Home(props) {
    return (
        <div className={"home"}>
            <Sidebar />
            <div className="homeContainer">
                <Navbar  />
                <div className="widgets">
                    <Widget type={"user"} />
                    <Widget type={"order"} />
                    <Widget type={"earning"} />
                    <Widget type={"balance"} />
                </div>
                <div className="charts">
                    <Featured />
                    <Chart />
                </div>
            </div>
        </div>
    );
}

export default Home;