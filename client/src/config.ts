const config : { [prop: string]: string } = {
    _apiUrl: 'http://localhost:8080/api/',
    get memAllocateUrl() {
        return this._apiUrl + 'memory/allocation'
    },
    get memGetUrl() {
        return this._apiUrl + 'memory'
    },
    get commandExecuteUrl() {
        return this._apiUrl + 'command';
    }
}

export {config};