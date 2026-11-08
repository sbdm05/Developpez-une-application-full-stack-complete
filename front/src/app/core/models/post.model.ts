export interface Post {
  id: number;
  title: string;
  content: string;
  authorUsername: string;
  topicId: number;
  topicName: string;
  createdAt: string; // date ISO envoyée par le back
}

export interface CreatePostRequest {
  topicId: number;
  title: string;
  content: string;
}

// paramètre ?sort= de /api/feed
export type SortOrder = 'asc' | 'desc'; 