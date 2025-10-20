import  {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import LoginPage from "./pages/login";
import ClaimDetail from "./pages/claimdetail";
import ClaimsPage from "./pages/claims";
import Dashboard from "./pages/dashboard";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path = "/" element={<LoginPage />} />
        <Route path = "/dashboard" element = {<ProtectedRoute> {<Dashboard />} </ProtectedRoute> } />
        <Route path = "/claims" element = {<ProtectedRoute> {<ClaimsPage />} </ProtectedRoute> } />
        <Route path = "claimdetail" element = {<ProtectedRoute> {<ClaimDetail />} </ProtectedRoute> } />
      </Routes>
    </Router>
  );
}
