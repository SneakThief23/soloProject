import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/login";
import Dashboard from "./pages/dashboard";
import ClaimsPage from "./pages/claims";
import ClaimDetail from "./pages/claimdetail";
import { Layout } from "./assets/layout";

// ProtectedRoute component
const ProtectedRoute: React.FC<{ children: React.JSX.Element }> = ({ children }) => {
  const token = localStorage.getItem("token");
  return token ? children : <Navigate to="/" replace />;
};

export default function App() {
  return (
    <Router>
      <Routes>
        <Route element={<Layout/>}>
        {/* Protected routes */}
        <Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>}/>
        <Route path="/claims" element={<ProtectedRoute><ClaimsPage /></ProtectedRoute>}/>
        <Route path="/claimdetail" element={<ProtectedRoute><ClaimDetail /></ProtectedRoute>}/>

        </Route>
        {/* Default route */}
        <Route path="/" element={<LoginPage />} />


      </Routes>
    </Router>
  );
}