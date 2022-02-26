export class Autorizacao {
    public uuid: string;
    public token: string;
    public username: string;
    public firstName: string;
    public lastName: string;
    public dateCreated: number;
    public timeToLive: number;
    public roles: Array<string> = [];

}
