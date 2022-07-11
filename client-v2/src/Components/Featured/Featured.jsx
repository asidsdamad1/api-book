import "./featured.scss";
import MoreVertIcon from "@mui/icons-material/MoreVert"
import {CircularProgressbar} from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css"
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown"
const Featured = () => {
    return (
        <div className={"featured"}>
            <div className="top">
                <div className="title">Total Revenue</div>
                <MoreVertIcon fontSize={"small"} />
            </div>
            <div className="bottom">
                <div className="featuredChart">
                    <CircularProgressbar value={70} text={"70%"} strokeWidth={5}/>
                </div>
                <p className="title">Total sales made today</p>
                <p className="amount">$400</p>
                <p className="des">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet architecto debitis dolorem enim exercitationem.</p>
                <div className="summary">
                    <div className="item">
                        <div className="itemTitle">Target</div>
                        <div className="itemResult negative">
                            <KeyboardArrowDownIcon fontSize={"small"}/>
                            <div className="resultAmount">
                                $12.4
                            </div>
                        </div>
                    </div>
                    <div className="item">
                        <div className="itemTitle">Last Week</div>
                        <div className="itemResult positive">
                            <KeyboardArrowDownIcon fontSize={"small"}/>
                            <div className="resultAmount">
                                $12.4
                            </div>
                        </div>
                    </div>
                    <div className="item">
                        <div className="itemTitle">Last Month</div>
                        <div className="itemResult positive">
                            <KeyboardArrowDownIcon fontSize={"small"}/>
                            <div className="resultAmount">
                                $12.4
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Featured;