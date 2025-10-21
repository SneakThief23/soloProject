import {NavLink, Link} from "react-router-dom";

export function Navbar(){
    return(
        <>
        <Link to="/"><button>Log In</button></Link>
        <Link to="/dashboard"><button>Dashboard</button></Link>
        <Link to="/claims"><button>Claims</button></Link>
        <Link to="/claimdetail"><button>Claim Detail</button></Link>
        </>
    )
}