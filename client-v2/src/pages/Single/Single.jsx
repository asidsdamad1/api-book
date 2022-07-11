import "./single.scss"
import Navbar from "../../Components/Navbar/Navbar";
import Sidebar from "../../Components/Sidebar/Sidebar";

function Single(props) {
    return (
        <div className={"single"}>
            <Sidebar  />
            <div className="singleContainer">
                <Navbar />
                <div className="top">
                    <div className="left">
                        <div className="editButton">Edit</div>
                        <h1 className="title">Information</h1>
                        <div className="item">
                            <img src="https://images.pexels.com/photos/1820770/pexels-photo-1820770.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"
                                 alt=""
                                 className="itemImg"/>
                            <div className="details">
                                <h1 className="itemTitle">Trung Hieu</h1>
                                <div className="detailItem">
                                    <span className="itemKey">Email:</span>
                                    <span className="itemValue">trunghieu@gmail.com</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Phone:</span>
                                    <span className="itemValue">+8402382922</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Address:</span>
                                    <span className="itemValue">213 Lang, Ha Noi</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Country:</span>
                                    <span className="itemValue">VietNam</span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div className="right">

                    </div>
                </div>
                <div className="bottom">
                </div>
            </div>
        </div>
    );
}

export default Single;