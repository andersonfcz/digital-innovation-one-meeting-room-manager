import { Component, OnInit } from '@angular/core';
import { RoomDetailsComponent } from '../room-details/room-details.component';
import {Observable} from 'rxjs';
import {Room} from '../model/room';
import {RoomService} from '../room.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  rooms: Observable<Room[]>;

  constructor(
    private roomService: RoomService,
    private router: Router
  ) {
  }


  ngOnInit() {
    this.reloadData();
  }

  reloadData(): void {
    this.rooms = this.roomService.getRoomList();
  }

  deleteRoom(id: number): void {
    this.roomService.deleteRoom(id).subscribe(
      data => {
        console.log(data);
        this.reloadData();
      }, error => console.log(error)
    );
  }

  roomDetails(id: number): void {
    this.router.navigate(['details', id]);
  }

  roomUpdate(id: number): void {
    this.router.navigate(['update', id]);
  }
}
