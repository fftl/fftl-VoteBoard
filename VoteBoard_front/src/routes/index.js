import { createRouter, createWebHistory } from 'vue-router';
import List from '@/components/votes/List.vue'; //게시판 리스트 컴포넌트 호출
import Add from '@/components/votes/Add.vue'; //게시판 리스트 컴포넌트 호출
import ListMy from '@/components/votes/ListMy.vue'; //게시판 리스트 컴포넌트 호출
import Detail from '@/components/votes/Detail.vue'; //게시판 리스트 컴포넌트 호출
import Main from '@/components/Main.vue'; //게시판 리스트 컴포넌트 호출

const routerHistory = createWebHistory();

const router = createRouter({
    history: routerHistory,
    routes: [
        {
            path: '/',
            component: Main,
        },
        {
            path: '/main',
            component: Main,
        },
        {
            path: '/list',
            component: List,
        },
        {
            path: '/detail',
            component: Detail,
        },
        {
            path: '/listMy',
            component: ListMy,
        },
        {
            path: '/add',
            component: Add,
        },
    ],
});

export default router;
