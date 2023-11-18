export const oktaConfig = {
    clientId: '0oadcbhv209jnJ3Ju5d7',
    issuer: 'https://dev-58317262.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    dissableHttpsCheck: true,
}