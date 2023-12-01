const config : { [prop: string]: string } = {
    _apiUrl: 'http://localhost:8080/api/',
    get memAllocateUrl() : string {
        return this._apiUrl + 'memory/allocation'
    },
    get memGetUrl() : string {
        return this._apiUrl + 'memory'
    },
    get commandExecuteUrl() : string {
        return this._apiUrl + 'command';
    },
    get programStoreUrl() : string {
        return this._apiUrl + 'command/store';
    },
}

export {config};