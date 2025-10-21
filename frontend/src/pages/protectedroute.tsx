import {Navigate} from "react-router-dom";

interface ProtectedRoute {
    children: React.ReactNode;
}

const ProtectedRoute: React.FC<ProtectedRoute> = ({children}) => {
    const token = localStorage.getItem("token");

    if (!token) {
        return <Navigate to = "/" replace />;
    }
return <>{children}</>
};
export default ProtectedRoute