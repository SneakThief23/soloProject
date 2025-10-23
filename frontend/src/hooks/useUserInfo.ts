import { useQuery } from "@tanstack/react-query";
import apiClient from "../api/client";

export interface User {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
}

const fetchUserInfo = async (showAll: boolean): Promise<User[]> => {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("No auth token found");

  const endpoint = showAll ? "/user" : "/user/me";
  const { data } = await apiClient.get(endpoint, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return data;
};

export const useUserInfo = (showAll: boolean) => {
  return useQuery<User[], Error>({
    queryKey: ["userInfo", showAll],
    queryFn: () => fetchUserInfo(showAll),
    staleTime: 1000 * 60,
  });
};