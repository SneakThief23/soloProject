export default function LoginButton() {
    const handleLogin = () => {
        window.location.href = "http://localhost:8080/oauth2/authorization/google";
    };

    return (
        <button
        onClick = {handleLogin}
        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
        >Sign In</button>
    );
}