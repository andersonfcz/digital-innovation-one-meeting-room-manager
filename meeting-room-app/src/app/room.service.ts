import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from './model/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private baseUrl = 'http://localhost:8080/api/v1/room';

  constructor(
    private httpClient: HttpClient

  ) { }

  getRoomList():Observable<any> {
    return this.httpClient.get(`${ this.baseUrl}`);
  }

  deleteRoom(id: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getRoom(id: number): Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/${id}`);
  }


  createRoom(room: Room): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, room);
  }

  updateRoom(id: number, room: Room): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, room);
  }
}
