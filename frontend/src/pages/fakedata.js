export const laboratory_description = '' +
    '# 📋 Описание работы в лаборатории\n' +
    '\n' +
    '## 🧪 Название задания\n' +
    'Разработка и проведение лабораторной работы по теме: **"Изучение алгоритмов сортировки на языке программирования Python"**\n' +
    '\n' +
    '## 🎯 Цель работы\n' +
    'Изучить и реализовать различные алгоритмы сортировки, провести сравнительный анализ их эффективности.\n' +
    '\n' +
    '## 📚 Теоретические знания\n' +
    'Перед выполнением лабораторной работы студент должен знать:\n' +
    '- Основы языка Python\n' +
    '- Принципы работы со списками\n' +
    '- Алгоритмы сортировки: пузырьковая сортировка, сортировка вставками, быстрая сортировка\n' +
    '\n' +
    '## 🛠 Задание\n' +
    '1. Реализовать следующие алгоритмы сортировки:\n' +
    '    - Пузырьковая сортировка\n' +
    '    - Сортировка вставками\n' +
    '    - Быстрая сортировка\n' +
    '2. Сравнить время выполнения каждого алгоритма на массивах различного размера (100, 1000, 10 000 элементов)\n' +
    '3. Построить график сравнения производительности (опционально)\n' +
    '4. Оформить отчёт по результатам лабораторной работы\n' +
    '\n' +
    '## 📎 Формат сдачи\n' +
    '- Python-скрипт с реализациями алгоритмов\n' +
    '- Скриншоты работы программы\n' +
    '- Отчёт в формате PDF или DOCX\n' +
    '\n' +
    '## 🕒 Срок сдачи\n' +
    '**До: 10 мая 2025 года**\n' +
    '\n' +
    '## 💡 Примечание\n' +
    'Допускается использование библиотек `time`, `random`, `matplotlib` (для построения графиков).\n'

export const generateTestData = () => {
    const statusOptions = ['passed', 'failed', 'time_limit', 'memory_limit'];

    return [
        {
            id: 1,
            date: '2025-04-25 09:15',
            status: 'failed',
            total: 50,
            passed: 32,
            status_list: Array(50).fill().map((_, i) => {
                if (i < 32) return 'passed';
                if (i < 42) return 'failed';
                if (i < 47) return 'time_limit';
                return 'memory_limit';
            }),
            time_response: Array(50).fill().map(() => parseFloat((Math.random() * 20 + 1).toFixed(2))),
            memory_used: Array(50).fill().map(() => parseFloat((Math.random() * 100 + 50).toFixed(2)))
        },
        {
            id: 2,
            date: '2025-04-24 14:30',
            status: 'passed',
            total: 50,
            passed: 50,
            status_list: Array(50).fill('passed'),
            time_response: Array(50).fill().map(() => parseFloat((Math.random() * 20 + 1).toFixed(2))),
            memory_used: Array(50).fill().map(() => parseFloat((Math.random() * 100 + 50).toFixed(2)))
        },
        {
            id: 3,
            date: '2025-04-23 16:45',
            status: 'failed',
            total: 50,
            passed: 12,
            status_list: Array(50).fill().map((_, i) => {
                if (i < 12) return 'passed';
                if (i < 32) return 'failed';
                if (i < 42) return 'time_limit';
                return 'memory_limit';
            }),
            time_response: Array(50).fill().map(() => parseFloat((Math.random() * 20 + 1).toFixed(2))),
            memory_used: Array(50).fill().map(() => parseFloat((Math.random() * 100 + 50).toFixed(2)))
        },
        {
            id: 4,
            date: '2025-04-22 11:20',
            status: 'time_limit',
            total: 50,
            passed: 38,
            status_list: Array(50).fill().map((_, i) => {
                if (i < 38) return 'passed';
                return 'time_limit';
            }),
            time_response: Array(50).fill().map(() => parseFloat((Math.random() * 20 + 1).toFixed(2))),
            memory_used: Array(50).fill().map(() => parseFloat((Math.random() * 100 + 50).toFixed(2)))
        },
        {
            id: 5,
            date: '2025-04-21 10:05',
            status: 'memory_limit',
            total: 50,
            passed: 45,
            status_list: Array(50).fill().map((_, i) => {
                if (i < 45) return 'passed';
                return 'memory_limit';
            }),
            time_response: Array(50).fill().map(() => parseFloat((Math.random() * 20 + 1).toFixed(2))),
            memory_used: Array(50).fill().map(() => parseFloat((Math.random() * 100 + 50).toFixed(2)))
        }
    ];
};

export const courses = [
    {
        title: "Programming Fundamentals",
        period: "Fall 2024",
        description: "Learn basic programming concepts with Python. Perfect for beginners starting their coding journey."
    },
    {
        title: "Web Development",
        period: "Spring 2025",
        description: "Build modern websites using HTML, CSS and JavaScript. Hands-on projects included."
    },
    {
        title: "Database Systems",
        period: "Winter 2024",
        description: "Introduction to SQL and NoSQL databases. Learn to design efficient database structures."
    },
    {
        title: "Mobile App Development",
        period: "Summer 2025",
        description: "Create cross-platform mobile applications using Flutter framework."
    },
    {
        title: "Data Structures",
        period: "Fall 2024",
        description: "Master essential data structures like arrays, linked lists, trees and graphs."
    },
    {
        title: "Algorithms",
        period: "Spring 2025",
        description: "Study algorithm design and analysis. Learn to solve complex problems efficiently."
    },
    {
        title: "Computer Networks",
        period: "Winter 2024",
        description: "Understand how networks operate. Covering protocols, architectures and security."
    },
    {
        title: "Operating Systems",
        period: "Summer 2025",
        description: "Explore OS concepts including processes, memory management and file systems."
    },
    {
        title: "Software Engineering",
        period: "Fall 2024",
        description: "Learn software development methodologies and best practices for team projects."
    },
    {
        title: "Artificial Intelligence",
        period: "Spring 2025",
        description: "Introduction to AI concepts including machine learning and neural networks."
    }
];
