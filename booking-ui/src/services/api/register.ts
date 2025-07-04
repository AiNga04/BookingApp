import axiosInstance from "@/services/axios";
import type { IRegister } from "@/models/register";

export async function registerRecruiter(payload: IRegister) {
  const response = await axiosInstance.post("/auth/register", payload);
  return response.data;
}
