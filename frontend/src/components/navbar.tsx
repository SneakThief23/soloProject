import {NavLink} from "react-router-dom";

const NavBar = () => {
    return (
        <nav>
            <ul>
                <li>
                    <NavLink to = "/login">Login</NavLink>
                </li>
                <li>
                    <NavLink to = "/dashboard">Dashboard</NavLink>
                </li>
                                <li>
                    <NavLink to = "/claims">Claims</NavLink>
                </li>
                                <li>
                    <NavLink to = "/claimdetail">Claim Details</NavLink>
                </li>
            </ul>
        </nav>
    );
};
export default NavBar