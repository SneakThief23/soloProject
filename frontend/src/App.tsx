import  {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import LoginPage from "./pages/login";
import ClaimDetail from "./pages/claimdetail";
import ClaimsPage from "./pages/claims";
import Dashboard from "./pages/dashboard";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="{baseUrl}/oauth2/callback" element = {<GoogleCallback />} />
        <Route path = "/" element={<LoginPage />} />
        <Route path = "/dashboard" element = {<Dashboard />} />
        <Route path = "/claims" element = {<ClaimsPage />} />
        <Route path = "claimdetail" element = {<ClaimDetail />} />
      </Routes>
    </Router>
  );
}
