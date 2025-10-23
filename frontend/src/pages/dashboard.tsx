import { useState } from "react";
import { useUserInfo } from "../hooks/useUserInfo";
import { useUserPlans } from "../hooks/useUserPlans";
import { useUserClaims } from "../hooks/useUserClaims";

interface DashboardProps {
  showAll: boolean;
}
export default function Dashboard({showAll}: DashboardProps) {

  const { data: user, isPending: userLoading, error: userError } = useUserInfo();
  const { data: plans, isPending: plansLoading, error: plansError, refetch: refetchPlans } = useUserPlans(showAll);
  const { data: claims, isPending: claimsLoading, error: claimsError, refetch: refetchClaims } = useUserClaims(showAll);

  const loading = userLoading || plansLoading || claimsLoading;
  const error = userError || plansError || claimsError;

  if (loading) {
    return (
      <div className="flex items-center justify-center h-screen bg-gray-900 text-gray-400">
        Loading dashboard...
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex items-center justify-center h-screen bg-gray-900 text-red-400">
        Error loading data, try again later.
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-900 text-white p-10">
      {/* Header */}
      <header className="mb-10 text-center">
        <h1 className="text-4xl font-bold">Welcome back {user?.firstname} {user?.lastname}.</h1>
        <p className="text-gray-400 mt-2">{user?.email}</p>
      </header>

      {/* Layout grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        {/* User Info Card */}
        <div className="bg-gray-800 p-6 rounded-2xl shadow-lg">
          <h2 className="text-xl font-semibold mb-3 border-b border-gray-700 pb-2">Profile Info</h2>
          <p><strong>Name:</strong> {user?.firstname} {user?.lastname}</p>
          <p><strong>Email:</strong> {user?.email}</p>
        </div>

        {/* Plans Card */}
        <div className="bg-gray-800 p-6 rounded-2xl shadow-lg col-span-1 md:col-span-2">
          <h2 className="text-xl font-semibold mb-3 border-b border-gray-700 pb-2">Plans</h2>
          {plans && plans.length > 0 ? (
            <ul className="divide-y divide-gray-700">
              {plans.map((plan) => (
                <li key={plan.id} className="py-3">
                  <div className="flex justify-between items-center">
                    <span className="font-medium">{plan.planName}</span>
                    <span className="text-sm text-gray-400">Tier: {plan.tier}</span>
                  </div>
                  <p className="text-gray-400 text-sm mt-1">${plan.monthlyCost}/month - {plan.coverage}</p>
                  <p className="text-gray-500 text-xs">Copay: ${plan.copay} | Deductible: ${plan.deductible}</p>
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-400">No plans found.</p>
          )}
        </div>

        {/* Claims Card */}
        <div className="bg-gray-800 p-6 rounded-2xl shadow-lg md:col-span-3">
          <h2 className="text-xl font-semibold mb-3 border-b border-gray-700 pb-2">Recent Claims</h2>
          {claims && claims.length > 0 ? (
            <table className="w-full text-sm">
              <thead>
                <tr className="text-gray-400 text-left border-b border-gray-700">
                  <th className="py-2">Provider</th>
                  <th className="py-2">Date</th>
                  <th className="py-2">Amount</th>
                  <th className="py-2">Status</th>
                </tr>
              </thead>
              <tbody>
                {claims.map((claim) => (
                  <tr key={claim.id} className="border-b border-gray-700 hover:bg-gray-700 transition">
                    <td className="py-2">{claim.provider}</td>
                    <td className="py-2">{new Date(claim.date).toLocaleDateString()}</td>
                    <td className="py-2">${claim.amount}</td>
                    <td className={`py-2 font-medium ${
                      claim.status === "APPROVED" ? "text-green-400" :
                      claim.status === "PENDING" ? "text-yellow-400" :
                      "text-red-400"
                    }`}>
                      {claim.status}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p className="text-gray-400">No claims found.</p>
          )}
        </div>
      </div>
    </div>
  );
}