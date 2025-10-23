import React, { useState, useEffect } from 'react';
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
  TextField,
  MenuItem,
  TablePagination,
  Alert,
  IconButton,
  Tooltip
} from '@mui/material';

import { Visibility } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';

//type definitions
interface Claim {
  id: string;
  claimNumber: string;
  memberId: string;
  providerId: string;
  serviceStartDate: string;
  serviceEndDate: string;
  receivedDate: string;
  totalBilled: number;
  totalAllowed: number;
  totalPlanPaid: number;
  totalMemberResponsibility: number;
  updatedAt: string;
  claimStatus: string;
  // Optional fields for display (from joined tables)
  memberName?: string;
  providerName?: string;
}

const ClaimsTable = () => {
    const navigate = useNavigate();
    const [claims, setClaims] = useState<Claim[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<String | null>(null);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [statusFilter, setStatusFilter] = useState('ALL');
    const [searchTerm, setSearchTerm] = useState('');

    //fetching from the api
    useEffect(() => {
        fetchClaims();
    }, []);

    const fetchClaims = async() => {
        try {
            setLoading(true);
            const token = localStorage.getItem('token');

            const response = await fetch('api/claims', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if(!response.ok) {
                throw new Error('Failed to fetch claims');
            }

            const data = await response.json();
            setClaims(data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err.message : 'An error has occurred');
        } finally {
            setLoading(false);
        }
    };

    //status color mapping
    const getStatusColor = (status: string): "default" | "success" | "warning" | "error" | "info" => {
        const statusLower = status.toLowerCase();
        if(statusLower.includes('approved') || statusLower.includes('paid')) {
            return 'success';
        }   else if (statusLower.includes('pending') || statusLower.includes('review')) {
            return 'warning';
        }   else if (statusLower.includes('denied') || statusLower.includes('rejeced')) {
            return 'error';
        }
        return 'default';
    };

    //claim filter
    const filteredClaims = claims.filter(claim => {
        const matchesStatus = statusFilter === 'ALL' || claim.claimStatus === statusFilter;
        const matchesSearch = 
        claim.claimNumber.toLowerCase().includes(searchTerm.toLowerCase()) ||
        (claim.memberName && claim.memberName.toLowerCase().includes(searchTerm.toLowerCase())) ||
        (claim.providerName && claim.providerName.toLowerCase().includes(searchTerm.toLowerCase()));
        return matchesStatus && matchesSearch;
    });

  //get unique status for filter dropdown
    const uniqueStatuses = Array.from(new Set(claims.map(c => c.claimStatus)));

  //paginaion
    const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
    };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
    };

    const handleViewClaim = (claimId: string) => {
        navigate(`/claim/${claimId}`);
    };

    //currency format
    const formatCurrency = (amount: number) => {
        return new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD'
        }).format(amount);
    };

    //date
    const formatDate = (dateString: string) => {
        return new Date(dateString).toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric'
        });
    };

    if (loading) {
        return (
            <Box display='flex' justifyContent='center' alignItems='center' minHeight='400px'>
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return (
            <Alert severity='error' sx={{ mb: 2}}>
                {error}
            </Alert>
        );
    }

    return (
        <Box sx={{ p:3 }}>
            <Typography variant='h5' gutterBottom sx={{ mb: 3, fontWeight: 600 }}>
                Claims Management
            </Typography>

            {/*Filters*/}
            <Box sx={{ mb: 3, display:'flex', gap:2, flexWrap:'wrap' }}>
                <TextField
                label='Search Claims'
                variant='outlined'
                size='small'
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                sx={{minWidth:250}}
                placeholder='Search by claim #, member, or provider'
                />

                <TextField
                select
                label='Status'
                value={statusFilter}
                onChange={(e) => setStatusFilter(e.target.value)}
                size='small'
                sx={{minWidth:150}}
                >
                <MenuItem value="ALL">All Statuses</MenuItem>
                {uniqueStatuses.map(status => (
                    <MenuItem key={status} value={status}>{status}</MenuItem>
                ))}
                </TextField>
        </Box>

        {/* table */}

              <TableContainer component={Paper} elevation={2}>
        <Table>
          <TableHead>
            <TableRow sx={{ bgcolor: 'primary.main' }}>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Claim Number</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Member</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Provider</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Service Date</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Received</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }} align="right">Billed</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }} align="right">Allowed</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }} align="right">Member Owes</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }}>Status</TableCell>
              <TableCell sx={{ color: 'white', fontWeight: 600 }} align="center">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredClaims
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((claim) => (
                <TableRow 
                  key={claim.id}
                  hover
                  sx={{ 
                    cursor: 'pointer',
                    '&:hover': { bgcolor: 'action.hover' }
                  }}
                  onClick={() => handleViewClaim(claim.id)}
                >
                  <TableCell sx={{ fontWeight: 500 }}>{claim.claimNumber}</TableCell>
                  <TableCell>{claim.memberName || claim.memberId}</TableCell>
                  <TableCell>{claim.providerName || claim.providerId}</TableCell>
                  <TableCell>
                    {claim.serviceStartDate === claim.serviceEndDate 
                      ? formatDate(claim.serviceStartDate)
                      : `${formatDate(claim.serviceStartDate)} - ${formatDate(claim.serviceEndDate)}`
                    }
                  </TableCell>
                  <TableCell>{formatDate(claim.receivedDate)}</TableCell>
                  <TableCell align="right" sx={{ fontWeight: 500 }}>
                    {formatCurrency(claim.totalBilled)}
                  </TableCell>
                  <TableCell align="right" sx={{ fontWeight: 500, color: 'success.main' }}>
                    {formatCurrency(claim.totalAllowed)}
                  </TableCell>
                  <TableCell align="right" sx={{ fontWeight: 600, color: 'error.main' }}>
                    {formatCurrency(claim.totalMemberResponsibility)}
                  </TableCell>
                  <TableCell>
                    <Chip 
                      label={claim.claimStatus} 
                      color={getStatusColor(claim.claimStatus)}
                      size="small"
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Tooltip title="View Details">
                      <IconButton 
                        size="small" 
                        color="primary"
                        onClick={(e) => {
                          e.stopPropagation();
                          handleViewClaim(claim.id);
                        }}
                      >
                        <Visibility />
                      </IconButton>
                    </Tooltip>
                  </TableCell>
                </TableRow>
              ))}
                       {filteredClaims.length === 0 && (
              <TableRow>
                <TableCell colSpan={10} align="center" sx={{ py: 4 }}>
                  <Typography color="text.secondary">
                    No claims found
                  </Typography>
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
        
        <TablePagination
          rowsPerPageOptions={[5, 10, 25, 50]}
          component="div"
          count={filteredClaims.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableContainer>
    </Box>
  );
};

export default ClaimsTable;