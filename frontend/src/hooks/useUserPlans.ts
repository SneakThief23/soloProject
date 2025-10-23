import { useQuery } from "@tanstack/react-query";
import apiClient from "../api/client";

export interface Plan {
  id: string;
  planName: string;
  tier: string;
  monthlyCost: number;
  coverage: string;
  copay: number;
  deductible: number;
}

//toggle debug flag to see all plans
const fetchUserPlans = async (showAll: boolean): Promise<Plan[]> => {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("No auth token found");

  const endpoint = showAll ? "/plans" : "/plans/user/me";
  const { data } = await apiClient.get(endpoint, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return data;
};

export const useUserPlans = (showAll: boolean) => {
  return useQuery<Plan[], Error>({
    queryKey: ["userPlans", showAll],
    queryFn: () => fetchUserPlans(showAll),
    staleTime: 1000 * 60,
  });
};