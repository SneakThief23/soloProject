import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useNavigate } from "react-router-dom";
import LoginPage from "./pages/login";
import Dashboard from "./pages/dashboard";
import ClaimsPage from "./pages/claims";
import ClaimDetail from "./pages/claimdetail";
import { Layout } from "./assets/layout";
import { Container } from "@mui/material";

const ProtectedRoute: React.FC<{ children: React.JSX.Element }> = ({ children }) => {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/" replace />;
};

// This component will parse the token from the URL
const OAuthHandler: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");

    if (token) {
      localStorage.setItem("token", token);
      // Remove token from URL for cleanliness
      window.history.replaceState({}, "", "/dashboard");
      // Navigate to dashboard
      navigate("/dashboard", { replace: true });
    } else {
      // No token found, redirect to login
      navigate("/", { replace: true });
    }
  }, [navigate]);

  return <div>Logging you in...</div>;
};

function App() {
  const [showAll, setShowAll] = useState<boolean>(false);

  return (
    <Container sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Router>
        <Routes>
          <Route element={<Layout showAll={showAll} setShowAll={setShowAll} />}>
            <Route
              path="/dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard showAll={showAll} />
                </ProtectedRoute>
              }
            />
            <Route
              path="/claims"
              element={
                <ProtectedRoute>
                  <ClaimsPage />
                </ProtectedRoute>
              }
            />
            <Route
              path="/claimdetail"
              element={
                <ProtectedRoute>
                  <ClaimDetail />
                </ProtectedRoute>
              }
            />
          </Route>

          {/* OAuth redirect handler */}
          <Route path="/oauth/callback" element={<OAuthHandler />} />

          {/* Default login route */}
          <Route path="/" element={<LoginPage />} />
        </Routes>
      </Router>
    </Container>
  );
}

export default App;