import { useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";

const LoginPage: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const token = params.get("token");

        if (token) {
            localStorage.setItem("token", token);
            navigate("/dashboard");
        }
    }, [navigate]);

    const handleGoogleLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google";
    };

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-gray-900 text-white">
            <h1 className="text-3xl font-bold mb-8">Woo</h1>
            <button
                onClick={handleGoogleLogin}
                className="bg-blue-600 hover:bg-blue-700 px-6 py-3 rounded-lg text-lg font-semibold"
            >
                Sign in with Google
            </button>
        </div>
    );
};

export default LoginPage;