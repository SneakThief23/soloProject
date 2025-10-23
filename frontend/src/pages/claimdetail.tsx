import React, { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Chip,
  CircularProgress,
  Box,
  Typography,
  Button,
  TablePagination,
  Tooltip,
  IconButton,
  TextField,
  MenuItem,
  Alert
} from "@mui/material";
import { Visibility } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";

interface Claim {
  id: string;
  claimNumber: string;
  memberName?: string;
  providerName?: string;
  serviceStartDate: string;
  serviceEndDate: string;
  receivedDate: string;
  totalBilled: number;
  totalAllowed: number;
  totalMemberResponsibility: number;
  claimStatus: string;
}

const ClaimsTable = () => {
  const navigate = useNavigate();
  const [claims, setClaims] = useState<Claim[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [showAll, setShowAll] = useState(false);

  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  // Fetch claims from API
  const fetchClaims = async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem("token");
      const endpoint = showAll ? "/claims/all" : "/claims/user/me";

      const response = await fetch(`http://localhost:8080/api${endpoint}`, {
        headers: {
          "Authorization": token ? `Bearer ${token}` : "",
          "Content-Type": "application/json"
        }
      });

      if (!response.ok) throw new Error("Failed to fetch claims");

      const data = await response.json();
      setClaims(data);
      setError(null);
    } catch (err) {
      setError(err instanceof Error ? err.message : "An error occurred");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchClaims();
  }, [showAll]); // refetch when toggle changes

  const handleViewClaim = (claimId: string) => {
    navigate(`/claim/${claimId}`);
  };

  const getStatusColor = (status: string): "default" | "success" | "warning" | "error" | "info" => {
    const s = status.toLowerCase();
    if (s.includes("approved") || s.includes("paid")) return "success";
    if (s.includes("pending") || s.includes("review")) return "warning";
    if (s.includes("denied") || s.includes("rejected")) return "error";
    return "default";
  };

  const formatCurrency = (amount: number) =>
    new Intl.NumberFormat("en-US", { style: "currency", currency: "USD" }).format(amount);

  const formatDate = (dateString: string) =>
    new Date(dateString).toLocaleDateString("en-US", { year: "numeric", month: "short", day: "numeric" });

  if (loading)
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    );

  if (error)
    return (
      <Alert severity="error" sx={{ mb: 2 }}>
        {error}
      </Alert>
    );

  return (
    <Box sx={{ p: 3 }}>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
        <Typography variant="h5" fontWeight={600}>
          Claims Management
        </Typography>
       {/* <Button
          variant="contained"
          color={showAll ? "secondary" : "primary"}
          onClick={() => setShowAll((prev) => !prev)}>
          {showAll ? "Show My Claims" : "Show All Claims"}
        </Button> */}
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Claim #</TableCell>
              <TableCell>Member</TableCell>
              <TableCell>Provider</TableCell>
              <TableCell>Service Date</TableCell>
              <TableCell>Received</TableCell>
              <TableCell align="right">Billed</TableCell>
              <TableCell align="right">Allowed</TableCell>
              <TableCell align="right">Member Owes</TableCell>
              <TableCell>Status</TableCell>
              <TableCell align="center">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {claims.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((claim) => (
              <TableRow key={claim.id} hover sx={{ cursor: "pointer" }} onClick={() => handleViewClaim(claim.id)}>
                <TableCell>{claim.claimNumber}</TableCell>
                <TableCell>{claim.memberName || "N/A"}</TableCell>
                <TableCell>{claim.providerName || "N/A"}</TableCell>
                <TableCell>
                  {claim.serviceStartDate === claim.serviceEndDate
                    ? formatDate(claim.serviceStartDate)
                    : `${formatDate(claim.serviceStartDate)} - ${formatDate(claim.serviceEndDate)}`}
                </TableCell>
                <TableCell>{formatDate(claim.receivedDate)}</TableCell>
                <TableCell align="right">{formatCurrency(claim.totalBilled)}</TableCell>
                <TableCell align="right" sx={{ color: "success.main" }}>
                  {formatCurrency(claim.totalAllowed)}
                </TableCell>
                <TableCell align="right" sx={{ color: "error.main" }}>
                  {formatCurrency(claim.totalMemberResponsibility)}
                </TableCell>
                <TableCell>
                  <Chip label={claim.claimStatus} color={getStatusColor(claim.claimStatus)} size="small" />
                </TableCell>
                <TableCell align="center">
                  <Tooltip title="View Details">
                    <IconButton size="small" color="primary" onClick={(e) => { e.stopPropagation(); handleViewClaim(claim.id); }}>
                      <Visibility />
                    </IconButton>
                  </Tooltip>
                </TableCell>
              </TableRow>
            ))}

            {claims.length === 0 && (
              <TableRow>
                <TableCell colSpan={10} align="center">
                  No claims found
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          count={claims.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={(e, newPage) => setPage(newPage)}
          onRowsPerPageChange={(e) => { setRowsPerPage(parseInt(e.target.value, 10)); setPage(0); }}
        />
      </TableContainer>
    </Box>
  );
};

export default ClaimsTable;