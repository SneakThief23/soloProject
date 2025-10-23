import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/login";
import Dashboard from "./pages/dashboard";
import ClaimsPage from "./pages/claims";
import ClaimDetail from "./pages/claimdetail";
import { Layout } from "./assets/layout";
import { Container } from "@mui/material";

// ProtectedRoute component
const ProtectedRoute: React.FC<{ children: React.JSX.Element }> = ({ children }) => {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/" replace />;
};

function App() {
  const [showAll, setShowAll] = useState<boolean>(false);

  return ( 
    <Container sx={{ bgcolor: 'darkgrey', minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <Router>
        <Routes>
          <Route element={<Layout showAll={showAll} setShowAll={setShowAll} />}>
            {/* Protected routes */}
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

          {/* Default route */}
          <Route path="/" element={<LoginPage />} />
        </Routes>
      </Router>
    </Container>
  );
}

export default App;