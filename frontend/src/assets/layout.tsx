import { Outlet } from "react-router-dom";
import { Navbar } from "../components/navbar";

interface LayoutProps {
  showAll: boolean;
  setShowAll: React.Dispatch<React.SetStateAction<boolean>>;
}

export function Layout({ showAll, setShowAll }: LayoutProps) {
  return (
    <div className="min-h-screen flex flex-col bg-gray-900 text-white">
      <Navbar showAll={showAll} setShowAll={setShowAll} />
      <main className="flex-grow p-6">
        <Outlet />
      </main>
    </div>
  );
}