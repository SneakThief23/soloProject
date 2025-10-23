import { Link } from "react-router-dom";

interface NavbarProps {
  showAll: boolean;
  setShowAll: React.Dispatch<React.SetStateAction<boolean>>;
}

export function Navbar({ showAll, setShowAll }: NavbarProps) {
  return (
    <nav className="flex gap-4 p-4 bg-gray-800 text-white items-center">
      <Link to="/"><button>Log In</button></Link>
      <Link to="/dashboard"><button>Dashboard</button></Link>
      <Link to="/claims"><button>Claims</button></Link>
      <Link to="/claimdetail"><button>Claim Detail</button></Link>

      {/* Toggle for debug mode */}
      <button
        className="ml-auto px-3 py-1 bg-blue-600 rounded hover:bg-blue-700"
        onClick={() => setShowAll(prev => !prev)}
      >
        {showAll ? "Show My Data" : "Show All Data"}
      </button>
    </nav>
  );
}