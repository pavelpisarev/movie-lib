import { useKeycloak } from "@react-keycloak/web";
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

export default function HomePage() {
    const { keycloak } = useKeycloak();
    return (
<Container disableGutters maxWidth="sm" component="main" sx={{ pt: 8, pb: 6 }}>
        <Typography
          component="h1"
          variant="h2"
          align="center"
          color="text.primary"
          gutterBottom
        >
        {keycloak.authenticated ? "Authenticated!" : "Unauthenticated :("}
        </Typography>
        {!keycloak.authenticated &&
        <Typography variant="h5" align="center" color="text.secondary" component="p">
          Please log in to view additional information
        </Typography>
        }
        {keycloak.authenticated &&
        <Typography variant="h5" align="center" color="text.secondary" component="p">
          <Typography>preferred username: {keycloak.tokenParsed.preferred_username}</Typography>
          <Typography>email: {keycloak.tokenParsed.email}</Typography>
          <Typography>can edit movies: {keycloak.tokenParsed.realm_access.roles.includes('MOVIE_EDIT') ? "yes" : "no"}</Typography>
          <Typography>can edit actors: {keycloak.tokenParsed.realm_access.roles.includes('ACTOR_EDIT') ? "yes" : "no"}</Typography>
          <Typography>can edit genres: {keycloak.tokenParsed.realm_access.roles.includes('GENRE_EDIT') ? "yes" : "no"}</Typography>
        </Typography>
        }
      </Container>
    );
}