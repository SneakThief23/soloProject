import { useQuery } from "@tanstack/react-query";
import apiClient from "../api/client";

export interface Claim {
  id: string;
  provider: string;
  date: string;
  amount: number;
  status: string;
}

const fetchUserClaims = async (showAll: boolean): Promise<Claim[]> => {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("No auth token found");

  const endpoint = showAll ? "/claims" : "/claims/user/me";
  const { data } = await apiClient.get(endpoint, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return data;
};

export const useUserClaims = (showAll: boolean) => {
  return useQuery<Claim[], Error>({
    queryKey: ["userClaims", showAll],
    queryFn: () => fetchUserClaims(showAll),
    staleTime: 1000 * 60,
  });
};